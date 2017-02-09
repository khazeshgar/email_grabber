package ir.khazehsgar.email;


public class grabber{
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public boolean grab(String u){


    }
    public boolean start(){

    }
    public static void main(String a[]){

    }
}