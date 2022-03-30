package com.epam.command;

import com.epam.command.implementation.AddToCart;
import com.epam.command.implementation.ApproveReceipt;
import com.epam.command.implementation.CartReset;
import com.epam.command.implementation.ChangeRole;
import com.epam.command.implementation.LoginCommand;
import com.epam.command.implementation.LogoutCommand;
import com.epam.command.implementation.PayForCart;
import com.epam.command.implementation.RegistrationCommand;
import com.epam.command.implementation.RequestReceipt;
import com.epam.command.implementation.RequestReceiptProlongation;
import com.epam.command.implementation.ShowAllMedicines;
import com.epam.command.implementation.ShowAllReceipts;
import com.epam.command.implementation.ShowAllUsers;
import com.epam.command.implementation.ShowOrder;
import com.epam.command.implementation.ShowOrderHistory;
import com.epam.command.implementation.ShowUserCart;
import com.epam.command.implementation.ShowUserReceipts;

public enum CommandType {
	LOGIN(new LoginCommand()),
	REGISTRATION (new RegistrationCommand()),
	LOGOUT(new LogoutCommand()),
	ADDTOCART(new AddToCart()),
	SHOWUSERCART(new ShowUserCart()),
	SHOWALLMEDICINES(new ShowAllMedicines()),
	REQUESTRECEIPT(new RequestReceipt()),
	SHOWUSERRECEIPTS(new ShowUserReceipts()),
	REQUESTRECEIPTPROLONGATION(new RequestReceiptProlongation()),
	PAYFORCART(new PayForCart()),
	SHOWORDERHISTORY(new ShowOrderHistory()),
	SHOWORDER(new ShowOrder()),
	CARTRESET(new CartReset()),
	APPROVERECEIPT(new ApproveReceipt()),
	SHOWALLRECEIPTS(new ShowAllReceipts()),
	SHOWALLUSERS(new ShowAllUsers()),
	CHANGEROLE(new ChangeRole());
	
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }
}
