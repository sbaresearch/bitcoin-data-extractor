package thesis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import thesis.thread.ExtractionService;

@SpringBootApplication
@EnableAsync
public class AnalyzerApplication implements CommandLineRunner {

	@Autowired
	private ExtractionService extractionThread;

	@Override
	public void run(String... args){
		extractionThread.run();
	}

	public static void main(String[] args) {
		SpringApplication.run(AnalyzerApplication.class, args).close();
	}
}
