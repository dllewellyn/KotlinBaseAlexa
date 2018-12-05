![Generic badge](https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiSThQWkJZd2wwZndKUThpQnB0MmUyTnhzNGxuZUcrQWRsRUpUVGZHbXVHWTdqcTNEQ1BLTkgzQ1ZFVnRxK25VZklINWtQY0ZYd281U2xlTlhTQmxXZFNFPSIsIml2UGFyYW1ldGVyU3BlYyI6ImhOZlpPV0NnQ0FQRFgvakoiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=master)

# Usage

Add the following to your gradle file

```
buildscript {

    ext.kotlin_version = '1.3.0'

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath "com.github.jengelman.gradle.plugins:shadow:4.0.2"
    }
}

plugins {
    id 'org.jetbrains.kotlin.jvm' version '1.3.0'
}

apply plugin: 'com.github.johnrengelman.shadow'

version '1.0'

repositories {
    mavenCentral()
    jcenter()
    maven {
        url  "https://dl.bintray.com/dllewellyn/kotlin-alexa-base"
    }
}

dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    implementation 'com.dan.llewellyn:KotlinBaseAlexa:0.9'
}

compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}
compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
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
