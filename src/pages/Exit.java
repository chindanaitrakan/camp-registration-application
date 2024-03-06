package pages;

/**
 * Class for the exit page.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class Exit extends Page{
    /**
     * Constructor for Exit
     * @param previousPage the previous page
     */
    public Exit(Page previousPage){
        super(previousPage);
    }
    /**
    * Main executable for this page
    * @return null
    */
    @Override
    public Page startExecution(){
        return null;
    }
}
