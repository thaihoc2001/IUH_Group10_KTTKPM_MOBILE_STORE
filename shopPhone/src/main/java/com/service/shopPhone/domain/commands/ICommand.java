package com.service.shopPhone.domain.commands;

import java.io.IOException;

public interface ICommand<TInput, TResponse> {
    TResponse execute(TInput input) throws IOException;
}
