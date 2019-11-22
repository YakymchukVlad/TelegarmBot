package com.khnu.yakymchuk.command;

import com.khnu.yakymchuk.request.IRequest;

public interface IActionCommand<T extends IRequest> {

    String execute(T request);

}
