# Generated by Django 4.0.3 on 2023-01-18 23:06

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('faculties_app', '0003_alter_department_bachelors_count_and_more'),
    ]

    operations = [
        migrations.AlterField(
            model_name='department',
            name='bachelors_count',
            field=models.IntegerField(default=0),
        ),
        migrations.AlterField(
            model_name='department',
            name='employee_count',
            field=models.IntegerField(default=0),
        ),
        migrations.AlterField(
            model_name='department',
            name='master_count',
            field=models.IntegerField(default=0),
        ),
        migrations.AlterField(
            model_name='department',
            name='year',
            field=models.IntegerField(default=1900),
        ),
    ]