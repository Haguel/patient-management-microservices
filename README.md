# Patient Management System

## Overview
The **Patient Management System** is a cloud-native microservice application built using **AWS Cloud Development Kit (CDK)** and deployed locally with **LocalStack**. It provides a scalable infrastructure for managing patient-related services, including authentication, billing, analytics, and patient data processing. The system leverages AWS services such as ECS Fargate, RDS (PostgreSQL), MSK (Kafka), and an Application Load Balancer, all defined in a Java-based CDK stack.

This project was developed by following the YouTube course:  
[**Build & Deploy a Production-Ready Patient Management System with Microservices: Java Spring Boot AWS**](https://www.youtube.com/watch?v=tseqdcFfTUY).

## Features
- **VPC**: A virtual private cloud with configurable Availability Zones for network isolation.
- **ECS Fargate Services**: Containerized microservices for authentication, billing, analytics, patient data, and API gateway.
- **RDS PostgreSQL**: Relational databases for authentication and patient services.
- **MSK Cluster**: Kafka-based messaging for analytics and billing services.
- **Application Load Balancer**: Exposes the API gateway service externally.
- **Health Checks**: Route 53 health checks for database instances.
- **LocalStack Integration**: Local testing and deployment without AWS cloud costs.

## Prerequisites
- **AWS CLI**: Configured with dummy credentials for LocalStack.
- **LocalStack**: For local AWS service emulation.
- **Docker**: For running LocalStack and containerized services.
- **Java 21**: For building the CDK application."
- **Maven**: For building the Java-based CDK application.

## Setup

### 1. Install Dependencies
- Install AWS CLI
- Install Docker
- Install LocalStack
- Install Java 21
- Install Maven
### 2. Configure AWS CLI for LocalStack
```bash
aws configure
AWS Access Key ID [None]: test
AWS Secret Access Key [None]: test
Default region name [None]: us-east-1
Default output format [None]: json
```
### 3. Create and start LocalStack Container
### 4. Clone the Repository
```bash
git clone https://github.com/Haguel/patient-management-microservices
cd patient-management-microservices
```
### 5.  Synthesize the CDK Stack
- Navigate to the `cdk` directory:
```bash
cd /patient-management/infrastructure/src/main/java/com/pm/stack
``` 
- Run the `main()` method in `LocalStack.java` to generate the CloudFormation template:
### 6. Deploy the CDK Stack
- Return to `/patient-management/infrastructure` directory
- Run the `localstack-deploy.sh` script to deploy the stack



