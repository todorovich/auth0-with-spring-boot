package net.todorovich.model;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder @Jacksonized
public class User
{
    private final String id;
}
