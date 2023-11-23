public class Book {
    private String isbn;
    private String tittle;
    private String author;
    private String quantity;
    private boolean loanStatus;
    private String deadline;
    private double payFine;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(boolean loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public double getPayFine() {
        return payFine;
    }

    public void setPayFine(double payFine) {
        this.payFine = payFine;
    }

    public static Book createBookEntry(String isbn, String tittle, String author, String quantity){
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTittle(tittle);
        book.setAuthor(author);
        book.setQuantity(quantity);

        return book;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", tittle='" + tittle + '\'' +
                ", author='" + author + '\'' +
                ", quantity='" + quantity + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }
}
