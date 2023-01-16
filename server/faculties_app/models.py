from django.db import models

# Create your models here.
class Faculty(models.Model):
    faculty_name  = models.CharField(max_length=255)

    class Meta:
        verbose_name = 'Faculty'
        verbose_name_plural = 'Faculties'

    def __str__(self) -> str:
        return self.faculty_name



class Department(models.Model):
    department_name  = models.CharField(max_length=255)
    year = models.IntegerField(default=1900)
    auditorium = models.CharField(max_length=10)
    boss = models.CharField(max_length=255)
    phone = models.CharField(max_length=60)
    email = models.CharField(max_length=60)
    employee_count = models.IntegerField(default=0)
    bachelors_count = models.IntegerField(default=0)
    master_count = models.IntegerField(default=0)
    faculty = models.ForeignKey(Faculty, on_delete=models.CASCADE)

    def __str__(self) -> str:
        return self.department_name