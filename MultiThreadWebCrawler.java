//jsoup connection in reference library
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

//(Runnable) means that it can be runnable on various different threads. (1/2) 
//This means the WebCrawler is Multi-threaded rather than Single-threaded. (2/2)
public class MultiThreadWebCrawler implements Runnable{
    private static final int MAX_DEPTH = 4;//Maximum depth the WebCrawler can traverse
    private Thread WebCThread;//thread for WebCralwer
    private String linkNum1;//This is the first link for the WebCrawler
    //keeps tracks  of the number of links the web crawler visits.
    private ArrayList<String> checkLinkOld = new ArrayList<String>();//a list for all oof the links the crawler goes through
    private int identify;

    //Constructor Method
    public MultiThreadWebCrawler(String linkConstruct, int valueConstruct) {
        linkNum1 = linkConstruct;
        identify = valueConstruct;
        //This starts the thread the bot will be using to run
        WebCThread = new Thread(this);
        WebCThread.start();
    }

    @Override
    public void run() {
        botCrawler(1, linkNum1);//starts at the first level, then traverses to the first link
    }

    //This allows the bots to use Web Crawling
    private void botCrawler(int DepthLevel, String URL) {
        if(DepthLevel <= MAX_DEPTH) {//makes the max depth not passed
            Document botDocument = JsoupReq (URL);//sends request to recieve document

            if(botDocument != null) {//succeed
                //Obtains both the link and URL
                for(Element botLink : botDocument.select("a[href]")) {
                    String linkSoonFu = botLink.absUrl("href");
                    if(checkLinkOld.contains(linkSoonFu) == false) {//fail
                        botCrawler(DepthLevel++, linkSoonFu);//visit next link if failed 
                    }
                }
            }
        }
    }

    //This gets the documents from the link
    private Document JsoupReq(String URL) {
        try {
            //Utilizes Jsoup in order to make a connection to links in the links used
            Connection connectOrgJsoup = Jsoup.connect(URL);
            Document docReq = connectOrgJsoup.get();//document

            if(connectOrgJsoup.response().statusCode()== 200) {//checks if connection works
                System.out.println("\nBot #" + identify + ": has collected data from this website: " + URL);//prints website url and bot ID
                String Heading = docReq.title();//title
                System.out.println(Heading);
                checkLinkOld.add(URL);//The URLs are placed into the list

                return docReq;
            }
            return null;
        }catch(IOException e) {//In the event of an error
            return null;
        }
    }

    public Thread obtain() {
        return WebCThread;//returns the thread for the designated WebCrawler
    }
}