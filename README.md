# Healthcheck-Client

Este projeto provê um serviço para monitorar URLs e informar em canais do Slack quando o HTTP Status Code for diferente de 200.

## FAQ
### Como posso testar?

Use o Docker para baixar a imagem e executar um container para testes.

- Baixe a imagem executando o docker pull:
```shell
docker pull seniocaires/healthcheck-client
```
- Para iniciar um container da imagem recém baixada:
```shell
docker run --rm -e HOSTS=http://localhost:4567/health,http://urlXYZ/check -e SLACK_CANAIS=general,canalxyz -e SLACK_TOKEN=token-slack-bot -e SLEEP=60 seniocaires/healthcheck-client
```

### Quais parâmetros são necessários (variáveis de ambiente)?
 - HOSTS : URLs monitoradas. Separadas por vírgula.
 - SLACK_CANAIS : Canais do Slack que serão informados quando alguma das URLs não responder com HTTP Status Code 200. Separados por vírgula.
 - SLACK_TOKEN : Token do Bot no Slack.
 - SLEEP : Tempo entre checagens. Tempo em segundos.
