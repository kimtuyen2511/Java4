/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.DAO;

import java.util.List;

/**
 *
 * @author My Laptop
 * @param <EntityType>
 * @param <KeyType>
 */
public abstract class ShopMyPhamDAO<EntityType, KeyType> {
    abstract public void insert(EntityType entity);
    abstract  public void update(EntityType entity);
    abstract public void delete(int id);
    abstract public EntityType selecteByID(int id);
    abstract public List<EntityType> selectAll();
    abstract public EntityType selectByKeyword(KeyType ten);
    abstract public List<EntityType> selectBySql(String sql, Object...args);
}
