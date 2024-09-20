#1: API Gateway
resource "aws_apigatewayv2_api" "ActionITemsAPI" {
  name          = "ActionItemsAPI"
  protocol_type = "HTTP"
 
}

#1: VPC Link
resource "aws_apigatewayv2_vpc_link" "actionItemsVPCLink" {
  name               = "ActionItemsVPCLink"
  security_group_ids = [aws_security_group.vpc_link-sg.id]
  subnet_ids         = [aws_subnet.private-us-east-1a.id, aws_subnet.private-us-east-1b.id]
}

#3: API Integration
resource "aws_apigatewayv2_integration" "api_integration" {
  api_id             = aws_apigatewayv2_api.ActionITemsAPI.id
  integration_type   = "HTTP_PROXY"
  connection_id      = aws_apigatewayv2_vpc_link.actionItemsVPCLink.id
  connection_type    = "VPC_LINK"
  description        = "VPC integration"
  integration_method = "ANY"
  integration_uri    = aws_alb_listener.action-items-http.arn
  depends_on         = [aws_alb.action-items-alb]
}

#4: APIGW Route
resource "aws_apigatewayv2_route" "default_route" {
  api_id    = aws_apigatewayv2_api.ActionITemsAPI.id
  route_key = "$default"
  target    = "integrations/${aws_apigatewayv2_integration.api_integration.id}"

}

resource "aws_apigatewayv2_route" "hello_route" {
  api_id    = aws_apigatewayv2_api.ActionITemsAPI.id
  route_key = "GET /hello"
  target    = "integrations/${aws_apigatewayv2_integration.api_integration.id}"
}

#5: APIGW Stage
resource "aws_apigatewayv2_stage" "default_stage" {
  api_id      = aws_apigatewayv2_api.ActionITemsAPI.id
  name        = "$default"
  auto_deploy = true
}

resource "aws_apigatewayv2_stage" "dev_stage" {
  api_id      = aws_apigatewayv2_api.ActionITemsAPI.id
  name        = "dev"
  auto_deploy = true
}

resource "aws_apigatewayv2_stage" "prod_stage" {
  api_id      = aws_apigatewayv2_api.ActionITemsAPI.id
  name        = "prod"
  auto_deploy = false
}


