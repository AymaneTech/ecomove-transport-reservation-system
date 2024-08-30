package com.wora.common.useCases;

public interface UseCase <Argument, Result> {
    Result execute(Argument arg);
}
