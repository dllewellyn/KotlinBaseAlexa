https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiSThQWkJZd2wwZndKUThpQnB0MmUyTnhzNGxuZUcrQWRsRUpUVGZHbXVHWTdqcTNEQ1BLTkgzQ1ZFVnRxK25VZklINWtQY0ZYd281U2xlTlhTQmxXZFNFPSIsIml2UGFyYW1ldGVyU3BlYyI6ImhOZlpPV0NnQ0FQRFgvakoiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=master

# Usage

Add the following to your gradle file

```
dependencies {
    compile 'com.dan.llewellyn:KotlinBaseAlexa:0.1'
}
```

Build your jar with shadow jar

```
./gradlew shadowJar

 aws lambda create-function --region eu-west-1 --function-name AlexaDemoSkill --zip-file "fileb://build/libs/KotlinBaseAlexa-1.0-SNAPSHOT-all.jar" --role arn:aws:iam::(your arn goes here):role/lambda_basic_execution --handler  EntryPoint --runtime java8 --timeout 60 --memory-size 256
```

To update in future

```
aws lambda update-function-code --region eu-west-1 --function-name AlexaDemoSkill --zip-file "fileb://build/libs/KotlinBaseAlexa-1.0-SNAPSHOT-all.jar"

```

In your lambda configuration to set the following environment variables:

ALEXA_ID - the ID for your alexa skill (you can get this from the alexa developer console it's called 'skill ID')