resource "random_password" "master" {
  length           = 16
  special          = true
  override_special = "_!%^"
}

resource "random_string" "uuid" {
  length           = 8
  special          = true
  override_special = "/_+=.@-"
}

resource "aws_secretsmanager_secret" "password" {
  name = "action-items-db-password-${random_string.uuid.result}"
}

resource "aws_secretsmanager_secret_version" "password" {
  secret_id     = aws_secretsmanager_secret.password.id
  secret_string = random_password.master.result
}
