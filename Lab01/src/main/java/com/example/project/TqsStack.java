package com.example.project;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TqsStack {
        private Integer limit = -1;
        private final ArrayList<Integer> stack;
        public TqsStack(){
            stack = new ArrayList<>();
        }

        public TqsStack(int limit){
            this.limit = limit;
            stack = new ArrayList<>();
        }

        public void push(Integer val){
            if(this.limit > 0) {
                if (this.stack.size() < limit)
                    this.stack.add(val);
                else
                    throw new IllegalStateException();
            }else {
                this.stack.add(val);
            }
        }

        public Integer pop(){
            if(this.stack.size() == 0){
                throw new NoSuchElementException();
            }else{
                return this.stack.remove(this.stack.size()-1); //Remove from list and return
            }
        }

        public Integer peek(){
            if(this.stack.size() == 0){
                throw new NoSuchElementException();
            }else{
                return this.stack.get(this.stack.size()-1);
            }
        }

        public Integer size(){
            return this.stack.size();
        }

        public boolean isEmpty(){
            return this.stack.size() == 0;
        }
    }


