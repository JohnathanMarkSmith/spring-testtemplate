package com.johnathanmarksmith.springresttemplate.Error;

/**
 * Date:   6/12/13 / 10:03 AM
 * Author: Johnathan Mark Smith
 * Email:  johnathansmith1969@gmail.com
 * <p/>
 * Comments:
 * <p/>
 * This is used to display error messages
 */

public class ErrorHolder
{


    public String errorMessage;


    public ErrorHolder()
    {
    }

    public ErrorHolder(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString()
    {
        return "ErrorHolder{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}