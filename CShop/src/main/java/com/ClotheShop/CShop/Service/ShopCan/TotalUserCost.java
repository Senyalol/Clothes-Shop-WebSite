package com.ClotheShop.CShop.Service.ShopCan;

import lombok.Data;

@Data
public class TotalUserCost<User,Cost> {

    private User user;
    private Cost cost;

    public TotalUserCost(User user, Cost cost) {
        this.user = user;
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "TotalUserCost [user=" + user + ", cost=" + cost + "]";
    }

}