## Error Handling Example

The code implements a custom AuthenticationProvider along with a replacement for the
**DefaultAuthorizationExceptionHandler**. The problem I'm trying to demonstrate is that when
an error/exception happens in the AuthenticationProvider, by the time the information gets
to the exception handling code, there is no trace of what really caused the error. The 
error handling code gets an AuthorizationException object, but nothing contained in that object
points back to the original error.

For testing I ran in debug mode and sent requests to _localhost:8080/test_ using simple BasicAuth
credentials. The code is designed to randomly break. I set a breakpoint in the **RejectionHandler** code
so I could inspect the contents of the **AuthorizationException** object. No matter how I triggered
the error, the exception object had no trace of the original error.

