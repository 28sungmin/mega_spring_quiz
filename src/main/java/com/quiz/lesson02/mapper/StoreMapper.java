package com.quiz.lesson02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.quiz.lesson02.domain.Store;

@Mapper
public interface StoreMapper {
	
	// input: (서비스로부터) X
	// output: (서비스에게) List<Store>
	public List<Store> selectStoreList();
	
}
