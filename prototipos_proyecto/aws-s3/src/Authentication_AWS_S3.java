import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public abstract class Authentication_AWS_S3 {

	
	/**
	 * Fuente https://javatutorial.net/java-s3-example
	 */
	AmazonS3 s3client;
	
	public Authentication_AWS_S3() {
		
		AWSCredentials credentials = new BasicAWSCredentials("asdfasdf", "asdgfasdf");
		s3client = new AmazonS3Client(credentials);
	}
	
}
