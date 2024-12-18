package io.github.santiagoangel;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import java.nio.file.Paths;

public class ConnectDatabase {

   public static void main(String[] args) {
         // Create the CqlSession object:
         try (CqlSession session = CqlSession.builder()
            .withCloudSecureConnectBundle(Paths.get("<</PATH/TO/>>secure-connect-depot.zip"))
            .withAuthCredentials("<<CLIENT ID>>","<<CLIENT SECRET>>")
            .build()) {
            // Select the release_version from the system.local table:
            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            //Print the results of the CQL query to the console:
            if (row != null) {
               System.out.println(row.getString("release_version"));
            } else {
               System.out.println("An error occurred.");
            }
         }
         System.exit(0);
   }
}
