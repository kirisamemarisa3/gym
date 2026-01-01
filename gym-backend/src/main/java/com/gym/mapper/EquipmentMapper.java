package com.gym.mapper;

import com.gym.entity.Common;

import com.gym.entity.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 器材 仓库类
 *
 * @author: ShanZhu
 * @date: 2024-08-01
 */
@Mapper
@Repository
public interface EquipmentMapper {
    List<Equipment> getAllEquipment(int page, int size);

    int addEquipment(Equipment equipment);

    int updateEquipment(Equipment equipment);

    int deleteEquipment(int equipmentNo);

    Common totalEquipment();

    List<Equipment> getByKeywordEquipment(String keyWord,int page,int size);

    Common totalEquipmentFuzzy(String keyWord);
}
