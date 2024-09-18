
output "alb_url" {
  value = "http://${aws_alb.action-items-alb.dns_name}"
}

output "db_instance_endpoint" {
  value = aws_db_instance.action_items_postgres_db.endpoint
}

output "db_instance_address" {
  value = aws_db_instance.action_items_postgres_db.address
}