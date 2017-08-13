import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		UploadS3 uploadS3 = new UploadS3();
		uploadS3.uploadFile();
		
		DownloadS3 downloadS3 = new DownloadS3();
	//	downloadS3.download();
	}
}
