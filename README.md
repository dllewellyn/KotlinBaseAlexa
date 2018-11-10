# Usage

Fork the library.
Clone it.

```
./gradlew shadowJar

 aws lambda create-function --region eu-west-1 --function-name KotlinAlexaBaseDemo --zip-file "fileb://build/libs/KotlinBaseAlexa-0.1-all.jar" --role arn:aws:iam::(your arn goes here):role/lambda_basic_execution --handler  EntryPoint --runtime java8 --timeout 60 --memory-size 512
```

To update in future

```
aws lambda update-function-code --region eu-west-1 --function-name AlexaDemoSkill --zip-file "fileb://build/libs/KotlinBaseAlexa-1.0-SNAPSHOT-all.jar"

```

You will need, in your lambda configuration to set the following environment variables:

ALEXA_ID - the ID for your alexa skill (you can get this from the alexa developer console it's called 'skill ID')