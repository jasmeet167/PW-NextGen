package com.csc.fsg.life.pw.web.controller;

public interface IAction {

	public void process(IRequest data, IResponse response) throws Exception;
}
