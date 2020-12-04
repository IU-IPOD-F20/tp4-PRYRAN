package application.action.context;

import application.utils.Reader;
import application.utils.Router;
import bank.BankAgency;

public class ContextWithRouter implements Context<ContextWithRouter.Container> {


    Container container;

    public ContextWithRouter(Reader reader, BankAgency bankAgency, Router router){
        container = new Container(reader,bankAgency,router);
    }

    @Override
    public Container content() {
        return container;
    }


    public class Container{
        Reader reader;
        BankAgency bankAgency;
        Router router;

        public Container(Reader reader, BankAgency bankAgency, Router router) {
            this.reader = reader;
            this.bankAgency = bankAgency;
            this.router = router;
        }

        public Reader getReader() {
            return reader;
        }

        public BankAgency getBankAgency() {
            return bankAgency;
        }

        public Router getRouter() {
            return router;
        }
    }
}
