


import java.io.*;
import java.util.*;

public class MovieAnalyzer {
  String pa;
  ArrayList<String> records = new ArrayList<>();
  ArrayList<String> Series_Title = new ArrayList<>();
  ArrayList<Integer> Released_Year = new ArrayList<>();
  ArrayList<String> Certificate = new ArrayList<>();
  ArrayList<Integer> Runtime = new ArrayList<>();
  ArrayList<String> Genre = new ArrayList<>();
  ArrayList<Float> IMDB_Rating = new ArrayList<>();
  ArrayList<String> Overview = new ArrayList<>();
  ArrayList<Integer> Meta_score = new ArrayList<>();
  ArrayList<String> Director = new ArrayList<>();
  ArrayList<String> Star = new ArrayList<>();
  ArrayList<Integer> Noofvotes = new ArrayList<>();
  ArrayList<String> Gross = new ArrayList<>();

    public void setPa(String pa) {
        this.pa = pa;
    }

    public void setRecords(ArrayList<String> records) {
        this.records = records;
    }

    public void setSeries_Title(ArrayList<String> series_Title) {
        this.Series_Title = series_Title;
    }

    public void setReleased_Year(ArrayList<Integer> released_Year) {
        this.Released_Year = released_Year;
    }

    public void setCertificate(ArrayList<String> certificate) {
        this.Certificate = certificate;
    }

    public void setRuntime(ArrayList<Integer> runtime) {
        this.Runtime = runtime;
    }

    public void setGenre(ArrayList<String> genre) {
        this.Genre = genre;
    }

    public void setIMDB_Rating(ArrayList<Float> IMDB_Rating) {
        this.IMDB_Rating = IMDB_Rating;
    }

    public void setOverview(ArrayList<String> overview) {
        this.Overview = overview;
    }

    public void setMeta_score(ArrayList<Integer> meta_score) {
        this.Meta_score = meta_score;
    }

    public void setDirector(ArrayList<String> director) {
        this.Director = director;
    }

    public void setStar(ArrayList<String> star) {
        this.Star = star;
    }


    public void setNoofvotes(ArrayList<Integer> noofvotes) {
        this.Noofvotes = noofvotes;
    }

    public void setGross(ArrayList<String> gross) {
        this.Gross = gross;
    }

    public MovieAnalyzer(String dataset_path) {
        this.pa = dataset_path;
        File csv = new File(this.pa);
        csv.setReadable(true);
        csv.setWritable(true);
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(new FileInputStream(csv), "UTF-8");
            br = new BufferedReader(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                if (line.toString().startsWith("\"")) {
                    records.add(line);
                    String[] strArr = line.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                    Series_Title.add((strArr[1].startsWith("\"") && strArr[1].endsWith("\"")) ? strArr[1].substring(1, strArr[1].length() - 1) : strArr[1]);
                    Released_Year.add(strArr[2].equals("") ? -1 : Integer.parseInt(strArr[2]));
                    Certificate.add((strArr[3].startsWith("\"") && strArr[3].endsWith("\"")) ? strArr[3].substring(1, strArr[3].length() - 1) : strArr[3]);
                    String sss[] = ((strArr[4].startsWith("\"") && strArr[4].endsWith("\"")) ? strArr[4].substring(1, strArr[4].length() - 1) : strArr[4]).split(" ");
                    Runtime.add(Integer.parseInt(sss[0]));
                    Genre.add((strArr[5].startsWith("\"") && strArr[5].endsWith("\"")) ? strArr[5].substring(1, strArr[5].length() - 1) : strArr[5]);
                    IMDB_Rating.add(strArr[6].equals("") ? -1 : Float.parseFloat(strArr[6]));
                    Overview.add((strArr[7].startsWith("\"") && strArr[7].endsWith("\"")) ? strArr[7].substring(1, strArr[7].length() - 1) : strArr[7]);
                    Meta_score.add(strArr[8].equals("") ? -1 : Integer.parseInt(strArr[8]));
                    Director.add((strArr[9].startsWith("\"") && strArr[9].endsWith("\"")) ? strArr[9].substring(1, strArr[9].length() - 1) : strArr[9]);
                    Star.add(((strArr[10].startsWith("\"") && strArr[10].endsWith("\"")) ? strArr[10].substring(1, strArr[10].length() - 1) : strArr[10]) + "," + ((strArr[11].startsWith("\"") && strArr[11].endsWith("\"")) ? strArr[11].substring(1, strArr[11].length() - 1) : strArr[11]) + "," + ((strArr[12].startsWith("\"") && strArr[12].endsWith("\"")) ? strArr[12].substring(1, strArr[12].length() - 1) : strArr[12]) + "," + ((strArr[13].startsWith("\"") && strArr[13].endsWith("\"")) ? strArr[13].substring(1, strArr[13].length() - 1) : strArr[13]));
                    Noofvotes.add(strArr[14].equals("") ? -1 : Integer.parseInt(strArr[14]));
                    Gross.add((strArr[15].startsWith("\"") && strArr[15].endsWith("\"")) ? strArr[15].substring(1, strArr[15].length() - 1) : strArr[15]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        int count1=0 ,count2=0,count3=0;
//        for (int i =0;i< Gross.size();i++){
//            if(Released_Year.get(i)==-1){count1++;}
//            if(IMDB_Rating.get(i)==-1){count2++;}
//            if(Gross.get(i).equals("")){count3++;}
//        }
//        System.out.println(count1);
//        System.out.println(count2);
//        System.out.println(count3);
    }//0

    public Map<Integer, Integer> getMovieCountByYear() {
        Map<Integer, Integer> abc = new TreeMap<Integer, Integer>(new MyComparator1());

        for (int i = 0; i < this.Released_Year.size(); i++) {
            if (!abc.containsKey(this.Released_Year.get(i))) {
                abc.put(this.Released_Year.get(i), 1);
            } else {
                int d = abc.get(this.Released_Year.get(i));
                abc.put(this.Released_Year.get(i), d + 1);
            }
        }

        return abc;
    }//1


    public Map<String, Integer> getMovieCountByGenre() {
        Map<String, Integer> abc = new TreeMap<>();
        for (int i = 0; i < this.Genre.size(); i++) {
            String hxka[] = this.Genre.get(i).split(", ");
            for (int j = 0; j < hxka.length; j++) {
                if (!abc.containsKey(hxka[j])) {
                    abc.put(hxka[j], 1);
                } else {
                    int d = abc.get(hxka[j]);
                    abc.put(hxka[j], d + 1);
                }
            }
        }
        Map<String, Integer> def = new LinkedHashMap<>();
        abc.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).
                forEach(e -> def.put(e.getKey(), e.getValue()));
        return def;
    }//2


    public Map<List<String>, Integer> getCoStarCount() {
        Map<List<String>, Integer> abc = new HashMap<>();
        String ghi[] = new String[2];
        for (int i = 0; i < this.Star.size(); i++) {
            String hxka[] = this.Star.get(i).split(",");
            for (int j = 0; j < hxka.length; j++) {
                for (int k = j + 1; k < hxka.length; k++) {
                    List<String> def = new ArrayList<>();
                    ghi[0] = hxka[j];
                    ghi[1] = hxka[k];
                    Arrays.sort(ghi);
                    def.add(ghi[0]);
                    def.add(ghi[1]);
                    if (!abc.containsKey(def)) {
                        abc.put(def, 1);
                    } else {
                        int d = abc.get(def);
                        abc.put(def, d + 1);
                    }
                }


            }

        }

        return abc;

    }//3


    public List<String> getTopMovies(int top_k, String by) {
        if (by.equals("runtime")) {
            List abc = new ArrayList<>();
            Map<String, String> def = new TreeMap<>();
            for (int i = 0; i < this.Series_Title.size(); i++) {
                if(!def.containsKey(this.Series_Title.get(i))){
                    def.put(this.Series_Title.get(i), this.Runtime.get(i).toString());}
                else {
                    def.put(this.Series_Title.get(i)+" ",this.Runtime.get(i).toString());
                }
            }
            List<Map.Entry<String, String>> entrys = new ArrayList<>(def.entrySet());
            Collections.sort(entrys, new MyComparator3());
            Map<String, Integer> efg = new HashMap<>();
            for (Map.Entry<String, String> entry : entrys) {
                abc.add(entry.getKey());
                efg.put(entry.getKey(), Integer.parseInt(entry.getValue()));

            }
            List zxc = new ArrayList<>();
            for (int j = 0; j < top_k; j++) {
                zxc.add(abc.get(j).toString().trim());
            }
            return zxc;
        } else if (by.equals("overview")) {
            List abc = new ArrayList<>();
            Map<String, String> def = new TreeMap<>();
            for (int i = 0; i < this.Series_Title.size(); i++) {
                def.put(this.Series_Title.get(i), String.valueOf(this.Overview.get(i).length()));
            }
            List<Map.Entry<String, String>> entrys = new ArrayList<>(def.entrySet());
            Collections.sort(entrys, new MyComparator3());
            Map<String, Integer> efg = new HashMap<>();
            for (Map.Entry<String, String> entry : entrys) {
                abc.add(entry.getKey());
                efg.put(entry.getKey(), Integer.parseInt(entry.getValue()));
            }
            List zxc = new ArrayList<>();
            for (int j = 0; j < top_k; j++) {
                zxc.add(abc.get(j));
//            System.out.println(abc.get(j));
            }
            return zxc;
        }
        return null;

    }//4


    public List<String> getTopStars(int top_k, String by) {
        if(by.equals("rating"))
        {
            Map<String, Double> abc = new TreeMap<>();//times
            Map<String, Double> def = new TreeMap<>();//rate
            Map<String, String> ghi = new TreeMap<>();//star,title
            for (int i = 0; i < this.Star.size(); i++) {
                String hxka[] = this.Star.get(i).split(",");
                for (int j = 0; j < hxka.length; j++) {
                    if (!ghi.containsKey(hxka[j])) {
                        if (!abc.containsKey(hxka[j])) {
                            abc.put(hxka[j], 1.0);
                            def.put(hxka[j], Double.valueOf(this.IMDB_Rating.get(i)));
                        } else {
                            double i1 = abc.get(hxka[j]);
                            double d1 = def.get(hxka[j]);
                            abc.put(hxka[j], i1 + 1);
                            def.put(hxka[j], d1 + this.IMDB_Rating.get(i));
                        }
                    }

                }
                ghi.clear();
            }

            Map<String, Double> jkl = new TreeMap<>();
            Iterator it = abc.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                Object val1 = abc.get(key);
                Object val2 = def.get(key);
                double v1 = (double) val1;
                double v2 = (double) val2;
                double v3 =  (v2/v1);
                jkl.put(key.toString(), v3);
            }

            List<Map.Entry<String, Double>> entrys = new ArrayList<>(jkl.entrySet());
            Collections.sort(entrys, new MyComparator4());
            List efg = new ArrayList<>();
            for (Map.Entry<String, Double> entry : entrys) {
                efg.add(entry.getKey());
            }
            List zxc = new ArrayList<>();
            for (int j = 0; j < top_k; j++) {
                zxc.add(efg.get(j));
//                System.out.println(efg.get(j));
            }
            return zxc;
        }

        else if (by.equals("gross")){
            Map<String, String> abc = new TreeMap<>();//times
            Map<String, String> def = new TreeMap<>();//gross
            Map<String, String> ghi = new TreeMap<>();//star,title
            for (int i = 0; i < this.Star.size(); i++) {
                if(!this.Gross.get(i).equals(""))
                {
                    String hxka[] = this.Star.get(i).split(",");
                    for (int j = 0; j < hxka.length; j++) {
                        if (!ghi.containsKey(hxka[j])) {
                            if (!abc.containsKey(hxka[j])) {
                                abc.put(hxka[j], "1");
                                def.put(hxka[j], this.Gross.get(i).replace(",",""));
                            } else {
                                int i1 = Integer.parseInt(abc.get(hxka[j]));
                                double d1 = Double.parseDouble(def.get(hxka[j]));
                                abc.put(hxka[j], String.valueOf(i1 + 1));
                                def.put(hxka[j], String.valueOf(d1 + Double.parseDouble(this.Gross.get(i).replace(",",""))));
                            }
                        }

                    }
                    ghi.clear();
                }
            }

            Map<String, String> jkl = new TreeMap<>();
            Iterator it = abc.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                Object val1 = abc.get(key);
                Object val2 = def.get(key);
                Double v1 = Double.parseDouble(val1.toString());
                Double v2 = Double.parseDouble(val2.toString());
                Double v3 = v2 / v1;
                jkl.put(key.toString(), String.valueOf(v3));
            }

            List<Map.Entry<String, String>> entrys = new ArrayList<>(jkl.entrySet());
            Collections.sort(entrys, new MyComparator5());
            List efg = new ArrayList<>();
            for (Map.Entry<String, String> entry : entrys) {
                efg.add(entry.getKey());
            }
            List zxc = new ArrayList<>();
            for (int j = 0; j < top_k; j++) {
                zxc.add(efg.get(j));
                System.out.println(efg.get(j));
            }
            return zxc;


        }
        return null;
    }//5


    public List<String> searchMovies(String genre, float min_rating, int max_runtime){
        Map<String, String> abc = new TreeMap<>();
        for(int i =0;i<this.records.size();i++){
            if (this.Genre.get(i).contains(genre)&&this.IMDB_Rating.get(i)>=min_rating&&this.Runtime.get(i)<=max_runtime){
                abc.put(this.Series_Title.get(i),String.valueOf(i));
            }
        }
        List<String> list = new ArrayList<>();
        Iterator it = abc.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            Object val = abc.get(key);
            list.add(key.toString());
        }
        return list;

    }//6


//        public static void main (String[]args){
//            String s = "D:\\IdeaProjects\\Java2\\src\\A1_Sample\\resources\\imdb_top_500.csv";
//            MovieAnalyzer MA = new MovieAnalyzer(s);
////    MA.getMovieCountByYear();
//    MA.getMovieCountByGenre();
////        MA.getTopMovies(100,"runtime");
////            MA.getTopStars(80,"rating");
//        }



}



class MyComparator1 implements Comparator<Integer> {
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
}


class MyComparator3 implements Comparator<Map.Entry> {
    public int compare(Map.Entry o1, Map.Entry o2) {
        if (Integer.parseInt((String) o1.getValue()) < Integer.parseInt((String) o2.getValue())) {
            return 1;
        } else if (Integer.parseInt((String) o1.getValue()) > Integer.parseInt((String) o2.getValue())) {
            return -1;
        } else {
            return ((String) o1.getKey()).compareTo((String) o2.getKey());
        }
    }
}
class MyComparator4 implements Comparator<Map.Entry> {
    public int compare(Map.Entry o1, Map.Entry o2) {
        if ((Double) o1.getValue() < (Double) o2.getValue()) {
            return 1;
        } else if ((Double) o1.getValue() > (Double) o2.getValue()) {
            return -1;
        } else {
            return ((String) o1.getKey()).compareTo((String) o2.getKey());
        }
    }
}

class MyComparator5 implements Comparator<Map.Entry> {
    public int compare(Map.Entry o1, Map.Entry o2) {
        if (Double.parseDouble((String) o1.getValue()) < Double.parseDouble((String) o2.getValue())) {
            return 1;
        } else if (Double.parseDouble((String) o1.getValue()) > Double.parseDouble((String) o2.getValue())) {
            return -1;
        } else {
            return ((String) o1.getKey()).compareTo((String) o2.getKey());
        }
    }
}