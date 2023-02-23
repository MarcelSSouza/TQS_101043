package com.example.project;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {
    public static TqsStack stack  = new TqsStack();

    @Test
    void itIsEmpty(){
        assertTrue(stack.isEmpty());
    }
    @Test
    void sizeZero(){
        TqsStack stack1  = new TqsStack();
        assertTrue(stack1.size() == 0);
    }

    @RepeatedTest(7)
    void afterNPushes(RepetitionInfo repetitionInfo){
        stack.push(repetitionInfo.getCurrentRepetition());
        assertEquals(true, stack.size() == repetitionInfo.getCurrentRepetition());
        assertFalse(stack.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {3,4,5,6,7,8,100,1209,129})
    void pushXPopX(int value){
        stack.push(value);
        assertTrue(stack.pop() == value);
    }


    @ParameterizedTest
    @ValueSource(ints = {3,4,5,6,7,8,100,1209,12229})
    void pushXPeekXSizeSame(int value){
        stack.push(value);
        int size = stack.size();
        assertTrue(stack.peek() == value && stack.size() == size);
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    void afterNPopsEmptyAndSizeZero(int value){
        for (int i = 0; i < value; i++) {
            stack.push(i);
        }
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            stack.pop();
        }
        assertTrue(stack.isEmpty() && stack.size() == 0);
    }

    @Test
    void popFromEmpty(){
            if(stack.isEmpty() == true){
                assertThrows(NoSuchElementException.class, () ->{
                    stack.pop();
                }, "Popping from an empty stack");
                }
            }

    @Test
    void peekFromEmpty(){
        if(stack.isEmpty() == true){
            assertThrows(NoSuchElementException.class, () ->{
                stack.peek();
            }, "Peeking from an empty stack");
        }
    }


    @Test
    void pushFull() {
        stack = new TqsStack(5);
        for(int i = 0 ; i < 5 ; i++){
            stack.push(i);
        }
        assertThrows(IllegalStateException.class, () -> {
            stack.push(1);
        });
    }}
