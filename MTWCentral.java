import java.util.ArrayList;

public class MTWCentral {
    public static void main(String[] args) {
        //Statement of Program
        System.out.println("This is a Multi-Threaded WebCrawler. ");
        System.out.println("It can explore the internet and access necessary information from the web pages it has visited.");
        System.out.println("This can simultaneously acess multiple different web pages due to it being Multi-Threaded.");
        
        ArrayList<MultiThreadWebCrawler> MTWCB = new ArrayList<>();//This array lists stores all of the instances for the WebCrawler
        MTWCB.add(new MultiThreadWebCrawler("https://www.nvidia.com/en-us/", 1));//Crawler for Website #1
        MTWCB.add(new MultiThreadWebCrawler("https://www.youtube.com", 2));//Crawler for Website #2
        MTWCB.add(new MultiThreadWebCrawler("https://www.apple.com", 3));//Crawler for Website #3
        MTWCB.add(new MultiThreadWebCrawler("https://www.publix.com", 4));//Crawler for Website #4
        
        //These bots will allow the WebCrawler to work simultaneously on multiple different threads. (1/3)
        //This makes the difference between a Single-threaded and MultiThreaded Web Crawler. (2/3)
        //Joins all of the finished threads together. (3/3)
        for(MultiThreadWebCrawler MTWC : MTWCB){
            try {
                MTWC.obtain().join();//allows threads to join
            }
            catch(InterruptedException e){
                e.printStackTrace();//solves exception issue
            }
        }
    }
}




