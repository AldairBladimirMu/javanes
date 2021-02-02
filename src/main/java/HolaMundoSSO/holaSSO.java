package HolaMundoSSO;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;


public class holaSSO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("vientos si esta quedando");
		
		String url = "http://app.personal-tasks.com:8080/auth/realms/master/protocol/openid-connect/token";

	            // add request headers
	            HashMap<String, String> parameters = new HashMap<>();
	            parameters.put("client_id", "admin-cli");
	            parameters.put("grant_type", "password");
	            parameters.put("username", "aldair");
	            parameters.put("password", "1*pruebas");
	            
	            
	            String form = parameters.keySet().stream()
	                    .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
	                    .collect(Collectors.joining("&"));
	            
	            HttpClient client = HttpClient.newHttpClient();
	            
	            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
	                    .headers("Content-Type", "application/x-www-form-urlencoded")
	                    .POST(BodyPublishers.ofString(form)).build();
	            HttpResponse<?> response = null;
				try {
					response = client.send(request, BodyHandlers.ofString());
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            System.out.println(response.statusCode() + response.body().toString());
	            /*MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
  				  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

  				  MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
   				  map.add("email", "first.last@example.com");
  
  				  HttpEntity<String> request = new HttpEntity<>(map, headers);
  				  String url = "url";

  				  ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);*/

	}

}
