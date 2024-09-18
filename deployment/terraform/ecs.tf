resource "aws_ecs_cluster" "action_items_cluster" {
  name = "action-items-cluster"

}

resource "aws_ecs_service" "action_items_service" {
  name            = "action-items-service"
  task_definition = aws_ecs_task_definition.action_items.arn
  launch_type     = "FARGATE"
  cluster         = aws_ecs_cluster.action_items_cluster.id
  network_configuration {
    assign_public_ip = false
    security_groups  = [aws_security_group.ecs_sg.id]
    subnets          = [aws_subnet.private-us-east-1a.id, aws_subnet.private-us-east-1b.id]
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.action_items.arn
    container_name   = "action-items"
    container_port   = "8080"
  }

  desired_count = 1
}

resource "aws_ecs_task_definition" "action_items" {
  family                = "action-items"
  container_definitions = <<EOF
    [
    {
      "name": "action-items",
      "image": "585008070670.dkr.ecr.us-east-1.amazonaws.com/action_items_server:latest",
      "portMappings": [
        {
          "containerPort": 8080
        }
      ],
      "environment": [
        {"name": "DB_HOST", "value": "${aws_db_instance.action_items_postgres_db.address}"},
        {"name": "DB_PORT", "value": "${aws_db_instance.action_items_postgres_db.port}"},
        {"name": "DB_USER", "value": "postgres"},
        {"name": "DB_PASS", "value": "${data.aws_secretsmanager_secret_version.password.secret_string}"}
    ],
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
          "awslogs-region": "us-east-1",
          "awslogs-group": "/ecs/action_items",
          "awslogs-stream-prefix": "ecs"
        }
      }
    }
  ]
  EOF

  execution_role_arn = aws_iam_role.action_items_task_execution_role.arn

  # These are the minimum values for Fargate containers.
  cpu                      = 512
  memory                   = 1024
  requires_compatibilities = ["FARGATE"]

  # This is required for Fargate containers (more on this later).
  network_mode = "awsvpc"

  depends_on = [aws_db_instance.action_items_postgres_db]
}

resource "aws_iam_role" "action_items_task_execution_role" {
  name               = "action_items-task-execution-role"
  assume_role_policy = data.aws_iam_policy_document.ecs_task_assume_role.json
}

# Attach the above policy to the execution role.
resource "aws_iam_role_policy_attachment" "ecs_task_execution_role" {
  role       = aws_iam_role.action_items_task_execution_role.name
  policy_arn = data.aws_iam_policy.ecs_task_execution_role.arn
}



