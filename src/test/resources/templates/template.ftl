OOO "Пакмаг 1"
${address}
тел. ${phone}
Номер кассы: ${boxOffice}
Док. No: ${documentNumber} *${cashier}
------------------------------------------------
<#list products as product>
${product?index}.${product.code} ${product.name} ${product.cost} * ${product.quantity} = ${product.totalCost}
</#list>

------------------------------------------------

СУММА ПО ЧЕКУ: ${totalCost}
<#if gotCash??>
ПОЛУЧЕНО НАЛИЧНЫМИ: ${gotCash}
СДАЧА: ${change}
</#if>
<#if gotCashless??>
ПОЛУЧЕНО БЕЗНАЛИЧНЫМИ: ${gotCashless}
</#if>

------------------------------------------------

<#if discountCardNo??>
ДИСКОНТНАЯ КАРТА No: ${discountCardNo}
ВАША СКИДКА: ${discount}

ЦЕНЫ УКАЗАНЫ С УЧЕТОМ СКИДКИ
</#if>

Фискальный документ No: ${fiscalDocumentNo}
ЭКЛЗ ${eklz}
Заводской No: ${factoryNo}
Регистр. N ККМ: ${kkm}
ИНН: ${inn}
${n0} ${date} ${time}
${n1} ${n2}

СПАСИБО ЗА ПОКУПКУ
МЫ ОТКРЫТЫ ЕЖЕДНЕВНО С 11 ДО 23
СОХРАНЯЙТЕ ПОЖАЛУЙСТА ЧЕК