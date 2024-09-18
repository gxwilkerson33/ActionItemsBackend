resource "aws_lb_target_group" "action_items" {
  name        = "action-items"
  port        = 8080
  protocol    = "HTTP"
  target_type = "ip"
  vpc_id      = aws_vpc.main.id

  health_check {
    enabled = true
    path    = "/hello"
  }

  depends_on = [aws_alb.action-items-alb]
}

resource "aws_alb" "action-items-alb" {
  name               = "action-items-alb"
  internal           = false
  load_balancer_type = "application"

  subnets = [aws_subnet.public-us-east-1a.id, aws_subnet.public-us-east-1b.id]

  security_groups = [ aws_security_group.alb_sg.id ]
  # security_groups = [aws_security_group.alb_sg.id]

  depends_on = [aws_internet_gateway.igw]
}

resource "aws_alb_listener" "action-items-http" {
  load_balancer_arn = aws_alb.action-items-alb.arn
  port              = "80"
  protocol          = "HTTP"
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.action_items.arn
  }
}

