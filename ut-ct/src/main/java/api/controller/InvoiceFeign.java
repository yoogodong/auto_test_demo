package api.controller;


public interface InvoiceFeign {
    public String createInvoice(Double totalPrice);
}
