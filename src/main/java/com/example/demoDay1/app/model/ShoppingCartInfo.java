package com.example.demoDay1.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoppingCartInfo {
    private long orderNum;
    private CustomerInfo customerInfo;
    private final List<ShoppingCartLineInfo> cartLines = new ArrayList<>();

    public boolean isEmpty(){
        return this.cartLines.isEmpty();
    }

    public void addBook(BookInfo bookInfo, int amount){
        ShoppingCartLineInfo lineInfo = this.getLineInfo(bookInfo.getId());
        if(lineInfo == null){
            lineInfo = new ShoppingCartLineInfo();
            lineInfo.setBookInfo(bookInfo);
            lineInfo.setQuantity(0);
            cartLines.add(lineInfo);
        }

        int newamount = lineInfo.getQuantity() + amount;

        if(newamount <= 0){
            cartLines.remove(lineInfo);
        }
        else
            lineInfo.setQuantity(newamount);
    }

    public ShoppingCartLineInfo getLineInfo(long id){
        for(ShoppingCartLineInfo info: cartLines){
            if(info.getBookInfo().getId() == id){
                return info;
            }
        }
        return null;
    }

    public void updateBook(long id, int amount){
        ShoppingCartLineInfo info = getLineInfo(id);
        if(info != null){
            if(amount <= 0 )
                cartLines.remove(info);
            else
                info.setQuantity(amount);
        }
    }

    public void removeBook(BookInfo bookInfo){
        ShoppingCartLineInfo info = this.getLineInfo(bookInfo.getId());
        if(info != null){
            cartLines.remove(info);
        }
    }

    public double getSumTotal(){
        double total = 0;

        for(ShoppingCartLineInfo line :this.cartLines){
            total += line.getSum();
        }

        return total;
    }


}
