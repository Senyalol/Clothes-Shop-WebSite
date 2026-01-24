package com.ClotheShop.CShop.Repository;

import com.ClotheShop.CShop.Entity.Locationshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationShopRepository extends JpaRepository<Locationshop,Integer> {

    Locationshop findById(int id);

}