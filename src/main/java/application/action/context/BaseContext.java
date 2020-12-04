package application.action.context;

import application.utils.Reader;
import bank.BankAgency;

public class BaseContext implements Context<BaseContext.Container> {

    Container container;

    public BaseContext(Reader reader, BankAgency bankAgency) {
        this.container = new Container(reader, bankAgency);
    }

    @Override
    public Container content() {
        return container;
    }

    public static class Container{
        Reader reader;
        BankAgency bankAgency;

        public Container(Reader reader, BankAgency bankAgency) {
            this.reader = reader;
            this.bankAgency = bankAgency;
        }

        public Reader getReader() {
            return reader;
        }

        public BankAgency getBankAgency() {
            return bankAgency;
        }
    }
}
