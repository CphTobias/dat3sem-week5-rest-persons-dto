package rest;

import com.google.gson.reflect.TypeToken;
import dtos.CountryDTO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("country")
public class CountryResource extends Provider {

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) throws IOException {
        URL url = new URL("https://restcountries.eu/rest/v1/alpha?codes=" + id);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr=null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        List<CountryDTO> countryDTO = GSON.fromJson(jsonStr, new TypeToken<List<CountryDTO>>() {}.getType());

        return Response.ok(GSON.toJson(countryDTO)).build();
    }

}