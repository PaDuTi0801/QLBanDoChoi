/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fpt.Dao;

import java.util.List;

/**
 *
 * @author HuynhQuangLe
 * @param <E>
 * @param <K>
 */
public interface DAO<E,K> {

    int insert(E entity);

    int update(E entity);

    int delete(E entity);

    String maxID();

    E selectById(K id);

    E selectByIdNoIsactive(K id);

    List<E> selectAll();

    List<E> selectByKeyword(String keyword);

    List<E> selectBySql(String sql, Object... args);
}
