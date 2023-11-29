package com.example.userservice.util;

import com.example.userservice.dto.TransactionRequestDto;
import com.example.userservice.dto.TransactionResponseDto;
import com.example.userservice.dto.TransactionStatus;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityDtoUtil {
    public static UserDto toDto(User user){
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user,dto);
        return dto;
    }

    public static User toEntity(UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        return user;
    }

    public static UserTransaction toEntity(TransactionRequestDto requestDto)
    {
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setUserId(requestDto.getUserId());
        userTransaction.setAmount(requestDto.getAmount());
        userTransaction.setTransactionDate(LocalDateTime.now());
        return userTransaction;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto requestDto, TransactionStatus status)
    {
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setUserId(requestDto.getUserId());
        transactionResponseDto.setAmount(requestDto.getAmount());
        transactionResponseDto.setStatus(status);
        return transactionResponseDto;
    }


}
