# RIPE Atlas API Contract Test Trigger

[ ![Codeship Status for sergeimikhailov/ripe-atlas-v2-contract-test-trigger](https://app.codeship.com/projects/b4d78040-f882-0134-33ee-76e4f316aab3/status?branch=master)](https://app.codeship.com/projects/210990) [![Coverage Status](https://coveralls.io/repos/github/sergeimikhailov/ripe-atlas-v2-contract-test-trigger/badge.svg?branch=master)](https://coveralls.io/github/sergeimikhailov/ripe-atlas-v2-contract-test-trigger?branch=master)

This project contains AWS Lambda function that will trigger latest build in Codeship for given Codeship project.

Written to trigger [ripe-atlas-v2-contract-test](//github.com/sergeimikhailov/ripe-atlas-v2-contract-test) build on schedule.
That repo contains contract test for RIPE NCC Atlas Probe API v2 which should run regularly. And free Codeship account does not provide scheduling facility ;-) 

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
export CODESHIP_PROJECT_ID=123456
export CODESHIP_API_KEY=f9d29eb15ad7471f98c1443735302259
./gradlew migrateFunction
```
