# RIPE Atlas API Contract Test Trigger

This project contains AWS Lambda function that will trigger latest build in Codeship for given Codeship project.

Written to trigger [ripe-atlas-v2-contract-test](//github.com/sergeimikhailov/ripe-atlas-v2-contract-test) build on schedule.

To build and test:
```bash
./gradlew clean build
```

To deploy:
```bash
export AWS_ACCESS_KEY_ID=AKIAIOSFODNN7EXAMPLE
export AWS_SECRET_ACCESS_KEY=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY
export AWS_REGION=ap-southeast-2
export AWS_LAMBDA_ROLE=arn:aws:iam::<account-id>:role/<aws-lambda-role-name>
./gradlew migrateFunction
```
