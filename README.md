# userRegisterService
User register service with validations fields and hashed password

Register service validates all the inputs through Spring validation (not blank fields, not null fields, regex patterns, and so on) and register the user.
This services validates also existing email and password match.
We used @ControllerAdice to handle exceptions and custom exceptions.
