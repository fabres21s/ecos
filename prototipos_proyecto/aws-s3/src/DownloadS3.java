import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

public class DownloadS3 extends Authentication_AWS_S3{

	public void download() throws IOException {
		
		String bucketName = "fileupload-test";
		try {
            System.out.println("Downloading an object");
            S3Object s3object = s3client.getObject(new GetObjectRequest(
            		bucketName, "peticion2.jmx"));
            System.out.println("Content-Type: "  + 
            		s3object.getObjectMetadata().getContentType());
            displayTextInputStream(s3object.getObjectContent());
            
           // Get a range of bytes from an object.
            
            GetObjectRequest rangeObjectRequest = new GetObjectRequest(
            		bucketName,"peticion2.jmx");
            //rangeObjectRequest.setRange(0, 10);
            S3Object object = s3client.getObject(rangeObjectRequest);
            
            S3ObjectInputStream objectContent = object.getObjectContent();
            IOUtils.copy(objectContent, new FileOutputStream("/Users/fabio/Desktop/peticion_downloaded.jmx"));
            
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which" +
            		" means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means"+
            		" the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
	
	private  void displayTextInputStream(InputStream input)
		    throws IOException {
		    	// Read one text line at a time and display.
		        BufferedReader reader = new BufferedReader(new 
		        		InputStreamReader(input));
		        while (true) {
		            String line = reader.readLine();
		            if (line == null) break;

		            System.out.println("    " + line);
		        }
		        System.out.println();
		    }
}
