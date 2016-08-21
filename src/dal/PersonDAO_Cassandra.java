package dal;

import static java.lang.System.out;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

import com.datastax.driver.*;
public class PersonDAO_Cassandra{
	   private Cluster cluster;

	   /** Cassandra Session. */

	   private Session session;

	   /**

	    * Connect to Cassandra Cluster specified by provided node IP

	    * address and port number.

	    *

	    * @param node Cluster node IP address.

	    * @param port Port of cluster host.

	    */

	   public void connect(final String node, final int port)

	   {

	      this.cluster = Cluster.builder().addContactPoint(node).withPort(port).build();

	      final Metadata metadata = cluster.getMetadata();

	      out.printf("Connected to cluster: %s\n", metadata.getClusterName());

	      for (final Host host : metadata.getAllHosts())

	      {

	         out.printf("Datacenter: %s; Host: %s; Rack: %s\n",

	            host.getDatacenter(), host.getAddress(), host.getRack());

	      }

	      session = cluster.connect();

	   }

	   /**

	    * Provide my Session.

	    *

	    * @return My session.

	    */

	   public Session getSession()

	   {

	      return this.session;

	   }

	   /** Close cluster. */

	   public void close()

	   {

	      cluster.close();

	   }
	   public static void main(final String[] args)

	   {

		   try {
			     
			      //Create a cluster object from your existing Cassandra cluster
			            Cluster cluster = HFactory.getOrCreateCluster("Test Cluster", "localhost:9160");
			             
			            //Create a keyspace object from the existing keyspace we created using CLI
			            Keyspace keyspace = HFactory.createKeyspace("AuthDB", cluster);
			             
			          //Create a mutator object for this keyspace using utf-8 encoding
			            Mutator<string> mutator = HFactory.createMutator(keyspace, stringSerializer);
			             
			            //Use the mutator object to delete row
			            mutator.delete("sample", "authCollection",null, stringSerializer);
			             
			            System.out.println("Data Deleted!!");
			             
			            //try to retrieve data after deleting
			            SliceQuery<String, String, String> sliceQuery = HFactory.createSliceQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
			            sliceQuery.setColumnFamily("authCollection").setKey("sample");
			            sliceQuery.setRange("", "", false, 4);
			             
			            QueryResult<ColumnSlice<String, String>> result = sliceQuery.execute(); 
			            System.out.println("\nTrying to Retrieve data after deleting the key 'sample':\n" + result.get());
			             
			            //close connection
			            cluster.getConnectionManager().shutdown();
			     
			  } catch (Exception ex) {
			   System.out.println("Error encountered while deleting data!!");
			   ex.printStackTrace() ;
			  }
	   }
	
}
