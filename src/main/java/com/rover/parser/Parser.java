package com.rover.parser;

import java.util.List;

public interface Parser {

    List<String> parse(String source) throws Exception;
}
