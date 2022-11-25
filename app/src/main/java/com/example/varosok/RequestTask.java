//package com.example.varosok;
//
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.Toast;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//public class RequestTask{
//    String requestUrl;
//    String requestType;
//    String requestParams;
//    private List<Varos> varosok=new ArrayList<>();
//
//    public RequestTask(String requestUrl, String requestType, String requestParams) {
//        this.requestUrl = requestUrl;
//        this.requestType = requestType;
//        this.requestParams = requestParams;
//    }
//
//    public RequestTask(String requestUrl, String requestType) {
//        this.requestUrl = requestUrl;
//        this.requestType = requestType;
//    }
//
//
//    protected Response doInBackground(Void... voids) {
//        Response response = null;
//        try {
//            switch (requestType) {
//                case "GET":
//                    response = RequestHandler.get(requestUrl);
//                    break;
//                case "POST":
//                    response = RequestHandler.post(requestUrl, requestParams);
//                    break;
//                case "PUT":
//                    response = RequestHandler.put(requestUrl, requestParams);
//                    break;
//                case "DELETE":
//                    response = RequestHandler.delete(requestUrl + "/" + requestParams);
//                    break;
//            }
//
//        } catch (IOException e) {
//            Toast.makeText(ListActivity.this,
//                    e.toString(), Toast.LENGTH_SHORT).show();
//        }
//        return response;
//    }
//
//
//    protected void onPostExecute(Response response) {
//        onPostExecute(response);
//
//        Gson converter = new Gson();
//        if (response.getResponseCode() >= 400) {
//            Toast.makeText(ListActivity.this,
//                    "Hiba történt a kérés feldolgozása során", Toast.LENGTH_SHORT).show();
//            Log.d("onPostExecuteError: ", response.getContent());
//        }
//        switch (requestType) {
//            case "GET":
//                Varos[] varosArray = converter.fromJson(response.getContent(), Varos[].class);
//                varosok.clear();
//                varosok.addAll(Arrays.asList(varosArray));
//                break;
//            case "POST":
//                Varos varos = converter.fromJson(response.getContent(), Varos.class);
//                varosok.add(0, varos);
//                break;
//            case "PUT":
//                Varos updatePerson = converter.fromJson(response.getContent(), Varos.class);
//                varosok.replaceAll(person1 ->
//                        person1.getId() == updatePerson.getId() ? updatePerson : person1);
//                break;
//            case "DELETE":
//                int id = Integer.parseInt(requestParams);
//                varosok.removeIf(person1 -> person1.getId() == id);
//                break;
//
//        }
//    }
//}
