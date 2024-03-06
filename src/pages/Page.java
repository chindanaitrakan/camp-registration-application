package pages;

/**
 * Superclass for other types of pages.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public abstract class Page {
    /**
     * Previous page visited.
     */
    private Page previousPage;
    /**
     * Constructor of Page.
     * @param previousPage The previous page.
     */
    public Page(Page previousPage){
        this.previousPage = previousPage;
    }
    /**
     * Main functionality of the page.
     * @return page The next page to be loaded.
     */
    public abstract Page startExecution();
    /**
     * Gets previous page visited
     * @return previous page visited
     */
    protected Page getPreviousPage() {
        if (this.previousPage != null)
            return this.previousPage;
        // if it's the first page
        else
            return this;
    }
}
