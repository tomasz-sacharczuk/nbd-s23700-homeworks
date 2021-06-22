import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.UpdateValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

public class Client {
    public static class Student {
        public String name;
        public String surname;
        public Boolean graduaded;
        public Integer tuition;
    }

    public static class StudentUpdate extends UpdateValue.Update<Student> {
        private final Student update;
        public StudentUpdate(Student update){
            this.update = update;
        }

        @Override
        public Student apply(Student student) {
            if(student == null) {
                student = new Student();
            }

            student.name = update.name;
            student.surname = update.surname;
            student.graduaded = update.graduaded;
            student.tuition = update.tuition;

            return student;
        }
    }

    private static RiakCluster setUpCluster() throws UnknownHostException {
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress("127.0.0.1")
                .withRemotePort(8087)
                .build();

        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();

        cluster.start();
        return cluster;
    }
    private static String fetch(Location location, RiakClient client){
        FetchValue fetchOp = new FetchValue.Builder(location)
                .build();
        RiakObject fetchedObject = null;
        try {
            fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        if(fetchedObject==null){
            return "No match for key: "+location.getKeyAsString();
        }
        return fetchedObject.getValue().toString();
    }

    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        RiakCluster cluster = null;
        try {
            cluster = setUpCluster();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        RiakClient client = new RiakClient(cluster);

            Student student = new Student();
            student.name = "Adam";
            student.surname = "Adamowicz";
            student.graduaded = false;
            student.tuition = 5000;

            Namespace s23700Bucket = new Namespace("s23700");
            Location adamLocation = new Location(s23700Bucket, "adam");
            StoreValue storeStudentOp = new StoreValue.Builder(student)
                    .withLocation(adamLocation)
                    .build();
            client.execute(storeStudentOp);
            System.out.println(fetch(adamLocation,client));

            student.tuition = 0;
            StudentUpdate updatedBook = new StudentUpdate(student);
            UpdateValue updateValue = new UpdateValue.Builder(adamLocation)
                    .withUpdate(updatedBook).build();
            client.execute(updateValue);
            System.out.println("Modified student: "+fetch(adamLocation,client));

            DeleteValue deleteOp = new DeleteValue.Builder(adamLocation)
                    .build();
            client.execute(deleteOp);
            System.out.println("Key: "+adamLocation.getKeyAsString() + " successfully removed ");


            System.out.println(fetch(adamLocation,client));

            cluster.shutdown();
    }
}